import { LitElement } from 'lit-element';
export declare class StoreList extends LitElement {
    static styles: import("lit-element").CSSResult;
    stores: Store[];
    constructor();
    private _getStoreReport;
    render(): import("lit-element").TemplateResult;
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
export {};
//# sourceMappingURL=store-list.d.ts.map