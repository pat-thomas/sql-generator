(ns sql-generator.core
  (:require [om.core             :as om]
            [om.dom              :as dom]
            [sql-generator.components.row :as row-cmp]
            [sql-generator.components.configuration :as configuration-cmp]
            [sql-generator.state :as state]
            )
  (:require-macros [om-utils.core :refer [defcomponent]]))

(enable-console-print!)

(defcomponent app
  (render
   (dom/div
    #js {:id "app"}
    (om/build row-cmp/me data)
    (om/build configuration-cmp/me data))))

(defn main
  []
  (om/root
   app
   state/app-state
   {:target (. js/document (getElementById "app-container"))}))

(defn reload-hook
  [& files]
  (println files)
  (main))

(main)
