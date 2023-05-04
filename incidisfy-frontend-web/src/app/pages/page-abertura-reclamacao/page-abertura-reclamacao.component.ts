import { Component, OnInit } from '@angular/core';
import { AbstractPages } from '../AbstractPages';
import { environment } from 'src/environments/environment';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Reclamacao } from 'src/app/models/reclamacao';
import { AlertType } from 'src/app/models/payloads/Alert';

@Component({
  selector: 'app-page-abertura-reclamacao',
  templateUrl: './page-abertura-reclamacao.component.html',
  styleUrls: ['./page-abertura-reclamacao.component.css']
})
export class PageAberturaReclamacaoComponent extends AbstractPages implements OnInit {

  private urlCliente = `${environment.api.cliente}`;
  private urlCategoria = `${environment.api.categoria}`;
  private urlProduto = `${environment.api.produto}`;
  private urlReclamacao = `${environment.api.reclamacao}`;

  public formAbertura!: FormGroup;

  // Inputs
  public cliente: any;

  // Combos
  public comboCategoria: any;
  public comboProduto: any;

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {
    super();
  }

  ngOnInit(): void {
    this.buildForm(new Reclamacao());

    // Inicializa os combos
    this.callCategoriaService();
    this.callProdutoService();
  }

  buildForm(reclamacao: Reclamacao): void {
    this.formAbertura = this.formBuilder.group({
      // Dados Cliente
      documento: new FormControl({ value: '', disabled: false }),
      codigoCliente: new FormControl({ value: reclamacao.codigoCliente, disabled: true }),
      descricaoCliente: new FormControl({ value: '', disabled: true }),

      // Dados categoria
      codigoCategoria: new FormControl({ value: reclamacao.codigoCategoria, disabled: false }),

      // Dados Produto
      codigoProduto: new FormControl({ value: reclamacao.codigoProduto, disabled: false }),

      // Dados Reclamacao
      descricao: new FormControl({ value: reclamacao.descricao, disabled: false }),
    });
  }

  /**
   * ---------------------------------------------------------------
   *
   *        Combos
   *
   * ---------------------------------------------------------------
   */


  callCategoriaService(): void {
    this.http.get(`${this.urlCategoria}`, { headers: super.getHeaders() }).subscribe({
      next: (data: any) => {
        this.comboCategoria = data;
      },
      error: (error) => {
        console.error('There was an error!', error);
        this.showMessage('Não foi possível efetuar a consulta por categorias!', AlertType.error);
      },
    });
  }

  callProdutoService(): void {
    this.http.get(`${this.urlProduto}`, { headers: super.getHeaders() }).subscribe({
      next: (data: any) => {
        this.comboProduto = data;
      },
      error: (error) => {
        console.error('There was an error!', error);
        this.showMessage('Não foi possível efetuar a consulta por produtos!', AlertType.error);
      },
    });
  }

  /**
   * ---------------------------------------------------------------
   *
   *        Actions
   *
   * ---------------------------------------------------------------
   */

  findConsumer(documento: string): void {
    var documentoFormat = documento.replace(/[^\d]/g, '');
    this.http.get(`${this.urlCliente}/` + documentoFormat, { headers: super.getHeaders() }).subscribe({
      next: (data: any) => {
        this.cliente = data;
      },
      error: (error) => {
        console.error('There was an error!', error);
        this.showMessage('Não foi possível efetuar a consulta por clientes!', AlertType.error);
      },
    });
  }

  insert(): void {
    const reclamacao = JSON.stringify(this.formAbertura.value);
    this.http.post(`${this.urlReclamacao}`, reclamacao, { headers: super.getHeaders() }).subscribe({
      next: (data) => {
        this.showMessage('Operação realizada com sucesso!', AlertType.info);
      },
      error: (error) => {
        console.error('There was an error!', error);
        this.showMessage('Não foi possível efetuar a operação!', AlertType.error);
      },
    });
  }
}
