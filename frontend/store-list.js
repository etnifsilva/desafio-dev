var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { LitElement, html, customElement, css, property } from 'lit-element';
let StoreList = class StoreList extends LitElement {
    constructor() {
        super();
        this.stores = [];
        this._getStoreReport();
    }
    _getStoreReport() {
        const myHeaders = new Headers();
        const requestOptions = {
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
        return html `
      <h1>Lista de lojas</h1>
      <div class="store-list">
        ${this.stores.map((store) => html `
          <div class="item-container">
            ${store.name}: ${store.total}
          </div>
        `)}
      </div>
    `;
    }
};
StoreList.styles = css `
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
      display: flex;
      justify-content: space-between;
    }
    `;
__decorate([
    property({ type: Array })
], StoreList.prototype, "stores", void 0);
StoreList = __decorate([
    customElement('store-list')
], StoreList);
export { StoreList };
//# sourceMappingURL=store-list.js.map