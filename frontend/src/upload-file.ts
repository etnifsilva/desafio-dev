import {LitElement, html, customElement, css, query} from 'lit-element';

@customElement('upload-file')
export class UploadFile extends LitElement {
  static styles = css`
    :host {
        display: flex;
        flex-direction: column;
        padding: 16px;
    }
  
    .upload-file {
      padding: 8px;
      border: solid 1px gray;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .button {
      background-color: #4CAF50; /* Green */
      border: none;
      color: white;
      padding: 15px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
    }
  `;

  @query('#file')
  _file: HTMLInputElement | undefined;

  private _onClick() {

    console.log(this._file);

    const myHeaders = new Headers();

    const formdata = new FormData();
    formdata.append("file", this._file!.files![0]);

    const requestOptions: RequestInit = {
      method: 'POST',
      headers: myHeaders,
      body: formdata,
      redirect: 'follow'
    };

    fetch("http://localhost:8081/store/transactions", requestOptions)
      .then(() => {
          window.location.href = './app/listagem.html';
      })
      .catch(error => console.log('error', error));
  }

  render() {
    return html`
      <h1>Upload de arquivos de transações</h1>
      <div class="upload-file">
        <input type="file" id="file" name="file">
        <button class="button" @click=${this._onClick}>
          Enviar arquivo
        </button>
      </div>
    `;
  }
}

declare global {
  interface HTMLElementTagNameMap {
    'upload-file': UploadFile;
  }
}
