def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
                  FILE=tier-${TIER_TEST}.log
                  if [[ -f "$FILE" ]]; then
                    sed -n '/short test summary info/, /Test result:/p' tier-${TIER_TEST}.log > tier${TIER_TEST}-summary.txt
                    sed -n '/short test summary info/, /Test result:/p' tier-${TIER_TEST}.log | awk '/passed/&&/failed/&&/skipped/' > slack-summary.txt
                    echo "Failures from ODF ${ODF_VERSION} tier ${TIER_TEST}" >> slack-summary.txt
                    sed -n '/short test summary info/, /Test result:/p' tier-${TIER_TEST}.log  | grep "FAILED" >> slack-summary.txt    
            '''
            slack-summary = readFile 'slack-summary.txt'
            env.MESSAGE = "Tier test summary:`${slack-summary}`"
        }
        catch (err) {
            echo 'Error ! Capturing tier test summary failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
