package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;
import com.thtf.deconfliction.service.FillingCaseProcessInfoService;
import com.thtf.deconfliction.service.dto.FillingCaseProcessInfoDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FillingCaseProcessInfo.
 */
@RestController
@RequestMapping("/api")
public class FillingCaseProcessInfoResource {

	private final Logger log = LoggerFactory.getLogger(FillingCaseProcessInfoResource.class);

	private static final String ENTITY_NAME = "fillingCaseProcessInfo";

	private final FillingCaseProcessInfoService fillingCaseProcessInfoService;

	public FillingCaseProcessInfoResource(FillingCaseProcessInfoService fillingCaseProcessInfoService) {
		this.fillingCaseProcessInfoService = fillingCaseProcessInfoService;
	}

	/**
	 * POST /filling-case-process-infos : Create a new fillingCaseProcessInfo.
	 *
	 * @param fillingCaseProcessInfoDTO
	 *            the fillingCaseProcessInfoDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new fillingCaseProcessInfoDTO, or with status 400 (Bad Request)
	 *         if the fillingCaseProcessInfo has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/filling-case-process-infos")
	@Timed
	public ResponseEntity<FillingCaseProcessInfoDTO> createFillingCaseProcessInfo(
			@RequestBody FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO) throws URISyntaxException {
		log.debug("REST request to save FillingCaseProcessInfo : {}", fillingCaseProcessInfoDTO);
		if (fillingCaseProcessInfoDTO.getId() != null) {
			throw new BadRequestAlertException("A new fillingCaseProcessInfo cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		FillingCaseProcessInfoDTO result = fillingCaseProcessInfoService.save(fillingCaseProcessInfoDTO);
		return ResponseEntity.created(new URI("/api/filling-case-process-infos/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /filling-case-process-infos : Updates an existing
	 * fillingCaseProcessInfo.
	 *
	 * @param fillingCaseProcessInfoDTO
	 *            the fillingCaseProcessInfoDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         fillingCaseProcessInfoDTO, or with status 400 (Bad Request) if
	 *         the fillingCaseProcessInfoDTO is not valid, or with status 500
	 *         (Internal Server Error) if the fillingCaseProcessInfoDTO couldn't
	 *         be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/filling-case-process-infos")
	@Timed
	public ResponseEntity<FillingCaseProcessInfoDTO> updateFillingCaseProcessInfo(
			@RequestBody FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO) throws URISyntaxException {
		log.debug("REST request to update FillingCaseProcessInfo : {}", fillingCaseProcessInfoDTO);
		if (fillingCaseProcessInfoDTO.getId() == null) {
			return createFillingCaseProcessInfo(fillingCaseProcessInfoDTO);
		}
		FillingCaseProcessInfoDTO result = fillingCaseProcessInfoService.save(fillingCaseProcessInfoDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fillingCaseProcessInfoDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /filling-case-process-infos : get all the fillingCaseProcessInfos.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         fillingCaseProcessInfos in body
	 */
	@GetMapping("/filling-case-process-infos")
	@Timed
	public ResponseEntity<List<FillingCaseProcessInfoDTO>> getAllFillingCaseProcessInfos(Pageable pageable) {
		log.debug("REST request to get a page of FillingCaseProcessInfos");
		Page<FillingCaseProcessInfoDTO> page = fillingCaseProcessInfoService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filling-case-process-infos");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /filling-case-process-infos/:id : get the "id"
	 * fillingCaseProcessInfo.
	 *
	 * @param id
	 *            the id of the fillingCaseProcessInfoDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         fillingCaseProcessInfoDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/filling-case-process-infos/{id}")
	@Timed
	public ResponseEntity<FillingCaseProcessInfoDTO> getFillingCaseProcessInfo(@PathVariable Long id) {
		log.debug("REST request to get FillingCaseProcessInfo : {}", id);
		FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO = fillingCaseProcessInfoService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fillingCaseProcessInfoDTO));
	}

	/**
	 * GET /filling-case-process-infos/:id : get the "id"
	 * fillingCaseProcessInfo.
	 *
	 * @param id
	 *            the id of the fillingCaseProcessInfoDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         fillingCaseProcessInfoDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/filling-case-process-infosx/{cid}")
	@Timed
	public ResponseEntity<Page<FillingCaseProcessInfoDTO>> getFillingCaseProcessInfoByCid(@PathVariable Long cid,
			@RequestParam Map<String, Object> params) {
		log.debug("REST request to get FillingCaseProcessInfo : {}", cid);
		String filterscount = (String) params.get("filterscount");
		if (filterscount == null || "".equals(filterscount)) {
			filterscount = "0";
		}
		params.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		params.put("filtervalue" + filterscount, cid.toString());
		params.put("filtercondition" + filterscount, "EQUAL");
		params.put("filterdatafield" + filterscount, "filingCaseId");
		params.put("filteroperator" + filterscount, "0");
		Page<FillingCaseProcessInfoDTO> page = fillingCaseProcessInfoService.findAll(params);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filling-case-process-infosx");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}

	/**
	 * DELETE /filling-case-process-infos/:id : delete the "id"
	 * fillingCaseProcessInfo.
	 *
	 * @param id
	 *            the id of the fillingCaseProcessInfoDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/filling-case-process-infos/{id}")
	@Timed
	public ResponseEntity<Void> deleteFillingCaseProcessInfo(@PathVariable Long id) {
		log.debug("REST request to delete FillingCaseProcessInfo : {}", id);
		fillingCaseProcessInfoService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
