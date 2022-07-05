---
layout: page.11ty.cjs
title: <upload-file> ⌲ Home
---

# &lt;upload-file>

`<upload-file>` is an awesome element. It's a great introduction to building web components with LitElement, with nice documentation site as well.

## As easy as HTML

<section class="columns">
  <div>

`<upload-file>` is just an HTML element. You can it anywhere you can use HTML!

```html
<upload-file></upload-file>
```

  </div>
  <div>

<upload-file></upload-file>

  </div>
</section>

## Configure with attributes

<section class="columns">
  <div>

`<upload-file>` can be configured with attributed in plain HTML.

```html
<upload-file name="HTML"></upload-file>
```

  </div>
  <div>

<upload-file name="HTML"></upload-file>

  </div>
</section>

## Declarative rendering

<section class="columns">
  <div>

`<upload-file>` can be used with declarative rendering libraries like Angular, React, Vue, and lit-html

```js
import {html, render} from 'lit-html';

const name="lit-html";

render(html`
  <h2>This is a &lt;upload-file&gt;</h2>
  <upload-file .name=${name}></upload-file>
`, document.body);
```

  </div>
  <div>

<h2>This is a &lt;upload-file&gt;</h2>
<upload-file name="lit-html"></upload-file>

  </div>
</section>
