#!/bin/bash

CONFIG_FILE="testLoad/resources/scenarios/load-template-config.json"

if [ ! -f $CONFIG_FILE ]; then
    echo "Config file not found!"
    exit 1
fi

BASE_URL=$(jq -r '.baseUrl' $CONFIG_FILE)
SCENARIOS=$(jq -c '.scenarios[]' $CONFIG_FILE)

for SCENARIO in $SCENARIOS; do
    SCENARIO_FILE="testLoad/resources/scenarios/$SCENARIO"

    if [ ! -f $SCENARIO_FILE ]; then
        echo "Scenario file $SCENARIO_FILE not found!"
        continue
    fi

    TEST_NAME=$(jq -r '.testName' $SCENARIO_FILE)
    ENDPOINT=$(jq -r '.endpoint' $SCENARIO_FILE)
    METHOD=$(jq -r '.method' $SCENARIO_FILE)
    NUMBER_OF_REQUESTS=$(jq -r '.numberOfRequests' $SCENARIO_FILE)
    RAMP_UP_TIME=$(jq -r '.rampUpTime' $SCENARIO_FILE)
    HEADERS=$(jq -c '.headers' $SCENARIO_FILE)
    BODY=$(jq -c '.body' $SCENARIO_FILE)
    PARAMS=$(jq -c '.params' $SCENARIO_FILE)
    DYNAMIC_ID=$(jq -r '.dynamicId // empty' $SCENARIO_FILE)

    if [ "$DYNAMIC_ID" == "true" ]; then
        # For dynamic ID, fetch the IDs dynamically (assuming you have a way to get IDs)
        IDS=$(curl -s -X GET "$BASE_URL/templates" | jq -r '.[].id')
    else
        IDS=("")
    fi

    echo "Running $TEST_NAME..."

    for ID in $IDS; do
        for ((i = 0; i < $NUMBER_OF_REQUESTS; i++)); do
            if [ "$DYNAMIC_ID" == "true" ]; then
                ENDPOINT_WITH_ID=$(echo $ENDPOINT | sed "s/{id}/$ID/")
            else
                ENDPOINT_WITH_ID=$ENDPOINT
            fi

            if [ "$METHOD" == "POST" ] || [ "$METHOD" == "PUT" ]; then
                curl -s -X $METHOD "$BASE_URL$ENDPOINT_WITH_ID" \
                    -H "Content-Type: application/json" \
                    -d "$BODY" &
            else
                curl -s -X $METHOD "$BASE_URL$ENDPOINT_WITH_ID" &
            fi

            sleep $(echo "scale=2; $RAMP_UP_TIME / $NUMBER_OF_REQUESTS" | bc)
        done
    done

    wait
    echo "$TEST_NAME completed."
done
