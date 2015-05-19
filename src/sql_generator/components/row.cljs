(ns sql-generator.components.row
  (:require [om.core :as om]
            [om.dom  :as dom])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent schema-input-box
  (render
   (dom/div
    nil
    (dom/label nil "Schema: ")
    (apply
     dom/select
     nil
     (map
      (fn [txt]
        (dom/option nil txt))
      (map :schema (get-in data [:config :schema-list])))))))

(defcomponent table-input-box
  (render
   (dom/div
    nil
    (dom/label nil "Table: ")
    (apply
     dom/select
     nil
     (map
      (fn [txt]
        (dom/option nil txt))
      (map :table (get-in data [:config :schema-list])))))))

(defcomponent add-new-btn
  (render
   (dom/button
    #js {:onClick (fn [_]
                    (println "ok"))}
    "Add new")))

(defcomponent me
  (render
   (dom/div
    #js {:className "row"}
    (om/build schema-input-box data)
    (om/build table-input-box data)
    (om/build add-new-btn data))))
