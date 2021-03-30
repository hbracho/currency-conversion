#!/bin/bash
for i in {1..200}
do
   curl http://35.193.0.83:30534/currency-conversion/from/USD/to/VEF/quantity/2000
done

stioctl install \
    --set values.kiali.enabled=true \
    --set "values.kiali.dashboard.jaegerURL=http://jaeger-query:16686" \
    --set "values.kiali.dashboard.grafanaURL=http://grafana.svc.default.:9090"