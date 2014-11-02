p(defpackage :aho.ex1
  (:use :cl)
  (:export #:ex1
           #:graph)
  )

(in-package :aho.ex1)

(defstruct vertice
  name edges color)

(defstruct graph
  vertices)

(defun add-vertice
    (graph v)
  (let ((vertice
          (cond
            ((not (vertice-p v)) (make-vertice :name v :edges '()))
            (t v))))
    (make-graph :vertices (cons vertice (graph-vertices graph)))))

(defun find-vertice-by-name
    (vertices name)
  (let ((first (car vertices))
        (rest (cdr vertices)))
    (format t "search to ~s from ~s~%" name first)
    ;(format t "vname ~s ~%" (vertice-name first))
    (cond
      ((equal first '()) nil)
      ((equal (vertice-name first) name) first)
      (t (find-vertice-by-name rest name)))))

(defun get-vertice
    (graph name)
  (let ((vertices (graph-vertices graph)))
    (find-vertice-by-name vertices name)))
    

(let ((v (make-vertice :name 'noname :edges '())))
  (vertice-name v))

(add-vertice (make-graph :vertices '()) 'any)

(let ((g (make-graph :vertices '())))
  (format t "~s" g))

(vertice-p (make-vertice :name 'test))
(setq g1 (make-graph :vertices '()))
(setq g1
      (add-vertice g1 (make-vertice :name 'test :edges '())))
(setq g1
      (add-vertice g1 (make-vertice :name 'test2 :edges '())))
(get-vertice g1 'test2)
