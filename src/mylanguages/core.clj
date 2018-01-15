(ns mylanguages.core
  (:gen-class)
  (:require [instaparse.core :as insta])
  (:import (clojure.asm    Opcodes Type ClassWriter)
	   (clojure.asm.commons Method GeneratorAdapter)
  )
)
;;parser vector
(def array-parser
   (insta/parser
      "<prog> = space add-sub space
       <add-sub> = mul-div | add | sub
       add = add-sub space <'+'> space mul-div
       sub = add-sub space <'-'> space mul-div
       <mul-div> = term | mul | div
       mul = mul-div space <'*'> space term
       div = mul-div space <'/'> space term
       <term> =  list | <'('> prog <')'>
       <space> = <#'[ ]'*>
       number = #'-?[0-9]*'
       list = <'['>(number | <space>)*<']'>
      "
   )
)

(def array-interpret
      {:number #(Long/parseLong %)
         :add (fn[a b] (map + a b))
         :sub (fn[a b] (map - a b))
         :mul (fn[a b] (map * a b))
         :div (fn[a b] (map / a b))
         :list (comp arr array)
      }
)
