package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@ApiModelProperty(example = "298.90")
    private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.00")
    private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "308.90")
    private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
    private String status;
	
	@ApiModelProperty(example = "2022-11-01T20:34:04Z")
    private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2022-11-01T20:34:04Z")
    private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2022-11-01T20:34:04Z")
    private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "2022-11-01T20:34:04Z")
    private OffsetDateTime dataCancelamento;
	
    private RestauranteApenasNomeModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoEntrega;
    private List<ItemPedidoModel> itens;   
}
