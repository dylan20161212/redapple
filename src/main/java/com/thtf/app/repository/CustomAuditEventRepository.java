package com.thtf.app.repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thtf.app.config.Constants;
import com.thtf.app.config.audit.AuditEventConverter;
import com.thtf.app.domain.PersistentAuditEvent;

/**
 * An implementation of Spring Boot's AuditEventRepository.
 */
@Repository
public class CustomAuditEventRepository implements AuditEventRepository {

	private static final String AUTHORIZATION_FAILURE = "AUTHORIZATION_FAILURE";
	private final AuditEventConverter auditEventConverter;
	protected static final int EVENT_DATA_COLUMN_MAX_LENGTH = 255;
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final PersistenceAuditEventRepository persistenceAuditEventRepository;

	public CustomAuditEventRepository(PersistenceAuditEventRepository persistenceAuditEventRepository,
			AuditEventConverter auditEventConverter) {

		this.persistenceAuditEventRepository = persistenceAuditEventRepository;
		this.auditEventConverter = auditEventConverter;
	}

	@Override
	public void add(AuditEvent event) {
		if (!AUTHORIZATION_FAILURE.equals(event.getType()) && !Constants.ANONYMOUS_USER.equals(event.getPrincipal())) {

			PersistentAuditEvent persistentAuditEvent = new PersistentAuditEvent();
			persistentAuditEvent.setPrincipal(event.getPrincipal());
			persistentAuditEvent.setAuditEventType(event.getType());
			persistentAuditEvent.setAuditEventDate(event.getTimestamp());
			Map<String, String> eventData = auditEventConverter.convertDataToStrings(event.getData());
			persistentAuditEvent.setData(truncate(eventData));
			persistenceAuditEventRepository.save(persistentAuditEvent);
		}
	}

	@Override
	public List<AuditEvent> find(String principal, Instant after, String type) {
		Iterable<PersistentAuditEvent> persistentAuditEvents;
		if (principal == null && after == null && type == null) {
			persistentAuditEvents = persistenceAuditEventRepository.findAll();
		} else if (after == null && type == null) {
			persistentAuditEvents = persistenceAuditEventRepository.findByPrincipal(principal);
		} else if (type == null) {
			persistentAuditEvents = persistenceAuditEventRepository.findByPrincipalAndAuditEventDateAfter(principal,
					after);
		} else {
			persistentAuditEvents = persistenceAuditEventRepository
					.findByPrincipalAndAuditEventDateAfterAndAuditEventType(principal, after, type);
		}
		return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
	}

	private Map<String, String> truncate(Map<String, String> data) {
		Map<String, String> results = new HashMap<>();

		if (data != null) {
			for (Map.Entry<String, String> entry : data.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					int length = value.length();
					if (length > EVENT_DATA_COLUMN_MAX_LENGTH) {
						value = value.substring(0, EVENT_DATA_COLUMN_MAX_LENGTH);
						log.warn(
								"Event data for {} too long ({}) has been truncated to {}. Consider increasing column width.",
								entry.getKey(), length, EVENT_DATA_COLUMN_MAX_LENGTH);
					}
				}
				results.put(entry.getKey(), value);
			}
		}
		return results;
	}

}
