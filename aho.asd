(require 'asdf)
(in-package :asdf)
(in-package :cl-user)


(defpackage #:aho-asd
  (:use :common-lisp :asdf))

(in-package :aho-asd)

(defsystem #:aho
  :author "cybergrind"
  :serial t
  :components
  ((:module "src"
            :serial t
            :components
            (
             (:file "tools")
             (:file "ex1")
             )
            ))
  )
