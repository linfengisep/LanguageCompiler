(ns mylanguages.core
  (:gen-class)
  (:require [instaparse.core :as insta])
  (:import (clojure.asm    Opcodes Type ClassWriter)
	   (clojure.asm.commons Method GeneratorAdapter)
  )
)
;;level-0, one dimension vector binary operation.
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
       list = <'['> (number | <space>)* <']'>
      "
   )
)
;;(filter (comp not zero?) [0 1 0 2 0 3 0 4])
;;(1 2 3 4)
(defn array-interpreter [ast]
      (insta/transform
         {:number #(Long/parseLong %)
            :add (fn[x y] (map + x y))
            :sub (fn[x y] (map - x y))
            :mul (fn[x y] (map * x y))
            :div (fn[x y] (map / x y))
            :list (comp list)
         } ast)
)

(def array-operation (->> "[1 2]-[3 4]" array-parser array-interpreter (into [])))
;array-operation

;(->> "[1 2]-[3 4]" array-parser array-interpreter (into []))
;(->> "[1 2]+[3 4]" array-parser array-interpreter (into []))
;(->> "[1 2]*[3 4]" array-parser array-interpreter (into []))
;(->> "[1 2]/[3 4]" array-parser array-interpreter (into []))


;;;;level-1 two dimension vector binary operation.
