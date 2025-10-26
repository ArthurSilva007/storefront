package br.com.dio.storefront.controller;

import br.com.dio.storefront.controller.request.ProductSaveRequest;
import br.com.dio.storefront.controller.response.ProductAvailableResponse;
import br.com.dio.storefront.controller.response.ProductDetailResponse;
import br.com.dio.storefront.controller.response.ProductSavedResponse;
import br.com.dio.storefront.mapper.IProductMapper;
import br.com.dio.storefront.service.IProductservice;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Log4j2
public class ProductController {

  private final IProductservice service;
  private final IProductMapper mapper;

  @PostMapping
  @ResponseStatus(CREATED)
    ProductSavedResponse ccreate(@RequestBody final ProductSaveRequest request) {
      var entity = mapper.toEntity(request);
      entity = service.save(entity);
      return mapper.toResponse(entity);
  }

  @PostMapping
    @ResponseStatus(NO_CONTENT)
    void purchase(@PathVariable final UUID Id) {
      service.purchase(Id);

  }

    @GetMapping
    List<ProductAvailableResponse> listAvailable() {
        var entities = service.findlAllActive();
        return mapper.toResponse(entities);
    }

    @GetMapping("{id}")
    ProductDetailResponse findById(@PathVariable final UUID id) {
        var dto = service.findInfo(id);
        return mapper.toResponse(dto);
    }

}
