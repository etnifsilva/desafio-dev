import { LitElement, html, customElement, css, property } from 'lit-element';

@customElement('store-list')
export class StoreList extends LitElement {
  static styles = css`
    :host {
        display: flex;
        flex-direction: column;
        padding: 16px;
    }

    .store-list {
      display: flex;
      align-items: space-between;
      flex-direction: column;
      justify-content: space-between;
    }

    .item-container {
      max-width: 420px;
      display: flex;
      justify-content: space-between;
    }

    .split-line {
      margin: 4px;
      flex-grow: 1;
      border-bottom-style: dotted;
      border-bottom-width: 1px;
      border-bottom-color: #000;
    }
    `;

  @property({ type: Array })
  stores: Store[] = [];

  constructor() {
    super();
    this._getStoreReport();
  }

  private _getStoreReport() {

    const myHeaders = new Headers();

    const requestOptions: RequestInit = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };

    fetch("http://localhost:8081/store/store-report", requestOptions)
      .then(response => response.text())
      .then(result => {
        const storeReport = JSON.parse(result);
        this.stores = storeReport.stores;
      })
      .catch(error => console.log('error', error));
  }
  
  render() {
    return html`
      <h1>Lista de lojas</h1>
      <div class="store-list">
        ${this.stores.map((store) => html`
          <div class="item-container">
            <div>
              ${store.name}
            </div>
            <div class="split-line"></div>
            <div>
              ${store.total}
            </div>
          </div>
        `)}
      </div>
    `;
  }
}

interface Store {
  name: string;
  total: number;
}

declare global {
  interface HTMLElementTagNameMap {
    'store-list': StoreList;
  }
}