(ns mobility-dpu.shims-sync
  (:use [mobility-dpu.protocols]
        [mobility-dpu.config]
        [aprint.core]
        [mobility-dpu.database]
        [mobility-dpu.datapoint])

  (:require [clj-http.client :as client]))

(def authorizations-endpoint (str (:shim-endpoint @config) "/authorizations"))
(def data-endpoint (str (:shim-endpoint @config) "/data"))


(defn get-available-endpoints
  "return service/endpoints that the user has authorize"
  [user sync-tasks]
  (let [body (get-in (client/get authorizations-endpoint
                                 {:query-params     {"username" user}
                                  :as               :json
                                  :throw-exceptions false})
                     [:body])
        auths (map keyword (mapcat :auths body))

        ]
    (select-keys sync-tasks auths)

    )
  )
(defn to-datapoint

  [user service schema body]
  (let [time (or (get-in body [:effective_time_frame :time_interval :start_date_time])
                 (get-in body [:effective_time_frame :date_time]))]
    (datapoint user "omh" (name schema) (name service) "SENSED" time time body)
    )

  )
(defn get-data
  "Send request to shims server to get the NORMALIZED data points.
  Return nil if the endpoint does not support normalization"
  [user service endpoint]
  (let [ret (client/get (str data-endpoint "/" (name service) "/" (name endpoint))
                        {:query-params     {"username"  user
                                            "normalize" "true"}
                         :as               :json
                         :throw-exceptions false})]
    (cond (= 200 (:status ret))
          (:body ret)
          ; Shims servcer will throw java.lang.UnsupportedOperationException if nomalization is not supported
          (.contains (:body ret) "java.lang.UnsupportedOperationException")
          nil
          :default
          (throw (Exception. (str ret)))
          )
    )
  )

(defn get-datapoints
  "Return normalized datapoints for the given users from the sync tasks"
  [user sync-tasks]
  (->>
    ; get service/endpoints (among the sync-tasks) this user has connected to
    (for [[service endpoints] (get-available-endpoints user sync-tasks)]
      ;for each endpoint
      (for [endpoint endpoints]
        ; get data points from the shims server
        (for [[schema datapoints] (:body (get-data user service endpoint))]
          (for [dp datapoints]
            ; convert the returned data point to the datapoint object that can be stored to the database
            (if dp
              (to-datapoint user service schema dp))
            )
          )
        )

      )
    (flatten)
    (filter identity))
  )

