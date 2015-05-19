(ns sql-generator.components.configuration
  (:require [om.core :as om]
            [om.dom  :as dom])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent toggle
  (render
   (dom/button
    #js {:onClick (fn [_]
                    (om/transact! data :show-config not)
                    (println :toggle))}
    "Edit Configuration")))

(defcomponent add-table-container
  (init-state {:schema nil
               :table  nil})
  (render-state
   (let [schema-table-friendly-name (str (om/get-state owner :schema) "." (om/get-state owner :table))]
     (dom/div
      nil
      (dom/div nil
               (dom/label nil "Schema:")
               (dom/input #js {:value    (om/get-state owner :schema)
                               :onChange (fn [evt]
                                           (om/set-state! owner :schema (.. evt -target -value)))}))
      (dom/div nil
               (dom/label nil "Table:")
               (dom/input #js {:value    (om/get-state owner :table)
                               :onChange (fn [evt]
                                           (om/set-state! owner :table (.. evt -target -value)))}))
      (dom/div nil (when-not (= schema-table-friendly-name ".")
                     schema-table-friendly-name))
      (dom/button
       #js {:onClick (fn [_]
                       (om/transact!
                        data
                        [:config :schema-list]
                        (fn [schema-tables]
                          (when (and (not (empty? (om/get-state owner :schema)))
                                     (not (empty? (om/get-state owner :table))))
                            (conj schema-tables {:schema (om/get-state owner :schema)
                                                 :table  (om/get-state owner :table)})))))}
       "+")))))

(defcomponent schema-list
  (render
   (dom/div
    nil
    (dom/label nil "Tables:")
    (apply
     dom/div
     nil
     (map (fn [{:keys [schema table]}]
            (dom/div nil (str schema "." table)))
          (sort (distinct (get-in data [:config :schema-list]))))))))

(defcomponent me
  (render
   (dom/div
    nil
    (om/build toggle data)
    (when (:show-config data)
      (apply
       dom/div
       nil
       (mapv
        (fn [cmp]
          (om/build cmp data))
        [add-table-container schema-list]))))))
