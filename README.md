aho
===

```
mkdir -p ~/.local/share/common-lisp/source
ln -s `pwd` ~/.local/share/common-lisp/source/aho
```

```lisp
(require 'asdf)
(require 'aho)

(aho:tt)
```
