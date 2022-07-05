import {UploadFile} from '../upload-file.js';
import {fixture, html} from '@open-wc/testing';

const assert = chai.assert;

suite('upload-file', () => {
  test('is defined', () => {
    const el = document.createElement('upload-file');
    assert.instanceOf(el, UploadFile);
  });

  test('renders with default values', async () => {
    const el = await fixture(html`<upload-file></upload-file>`);
    assert.shadowDom.equal(
      el,
      `
      <h1>Hello, World!</h1>
      <button part="button">Click Count: 0</button>
      <slot></slot>
    `
    );
  });

  test('renders with a set name', async () => {
    const el = await fixture(html`<upload-file name="Test"></upload-file>`);
    assert.shadowDom.equal(
      el,
      `
      <h1>Hello, Test!</h1>
      <button part="button">Click Count: 0</button>
      <slot></slot>
    `
    );
  });

  test('handles a click', async () => {
    const el = (await fixture(html`<upload-file></upload-file>`)) as UploadFile;
    const button = el.shadowRoot!.querySelector('button')!;
    button.click();
    await el.updateComplete;
    assert.shadowDom.equal(
      el,
      `
      <h1>Hello, World!</h1>
      <button part="button">Click Count: 1</button>
      <slot></slot>
    `
    );
  });
});
