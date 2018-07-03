package io.choerodon.test.manager.api.controller.v1;

import io.choerodon.swagger.annotation.Permission;
import io.choerodon.test.manager.api.dto.TestStatusDTO;
import io.choerodon.test.manager.app.service.TestStatusService;
import io.choerodon.core.exception.CommonException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by 842767365@qq.com on 6/25/18.
 */
@RestController
@RequestMapping(value = "/v1/status")
public class TestStatusController {

    @Autowired
    TestStatusService testStatusService;

	@Permission(permissionPublic = true)
	@ApiOperation("查询状态")
	@PostMapping("/query")
	public ResponseEntity<List<TestStatusDTO>> query(@RequestBody TestStatusDTO testStatusDTO) {
		return Optional.ofNullable(testStatusService.query(testStatusDTO))
				.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElseThrow(() -> new CommonException("error.testStatus.query"));

	}

	@Permission(permissionPublic = true)
	@ApiOperation("删除状态")
	@DeleteMapping
	public ResponseEntity delete(@RequestBody TestStatusDTO testStatusDTO) {
		testStatusService.delete(testStatusDTO);
		return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
	}

	@Permission(permissionPublic = true)
	@ApiOperation("插入状态")
	@PostMapping
	public ResponseEntity<TestStatusDTO> insert(@RequestBody TestStatusDTO testStatusDTO) {
		return Optional.ofNullable(testStatusService.insert(testStatusDTO))
				.map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
				.orElseThrow(() -> new CommonException("error.testStatus.insert"));
	}

	@Permission(permissionPublic = true)
	@ApiOperation("更新状态")
	@PutMapping
	public ResponseEntity<TestStatusDTO> update(@RequestBody TestStatusDTO testStatusDTO) {
		return Optional.ofNullable(testStatusService.update(testStatusDTO))
				.map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
				.orElseThrow(() -> new CommonException("error.testStatus.update"));
	}
}