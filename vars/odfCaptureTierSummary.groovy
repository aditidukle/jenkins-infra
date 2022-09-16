def call(){
    script {
        ansiColor('xterm') {
            echo ""
        }
        try {
            sh '''
                  FILE=tier${TIER_TEST}.log
                  if [[ -f "$FILE" ]]; then
                    sed -n '/short test summary info/, /Test result:/p' tier${TIER_TEST}.log > tier${TIER_TEST}-summary.txt
                    sed -n '/short test summary info/, /Test result:/p' tier${TIER_TEST}.log | awk '/passed/&&/failed/&&/skipped/' > slacksummary.txt
                    echo "Failures from ODF ${ODF_VERSION} tier ${TIER_TEST}" >> slacksummary.txt
                    sed -n '/short test summary info/, /Test result:/p' tier${TIER_TEST}.log  | grep "FAILED" >> slacksummary.txt
                    cp slacksummary.txt ${WORKSPACE}
                   else
                     echo "tier${TIER_TEST}.log file not found" > slacksummary.txt
                   fi
            '''
            slacksummary = readFile 'slacksummary.txt'
            env.MESSAGE = "Tier test summary:`${slacksummary}`"
        }
        catch (err) {
            echo 'Error ! Capturing tier test summary failed!'
            env.FAILED_STAGE=env.STAGE_NAME
            throw err
        }
    }
}
