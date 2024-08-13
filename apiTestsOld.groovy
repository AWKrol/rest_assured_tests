import myUtils.groovy

timeout(60){
    node("maven"){
        prepareConfig()
        def jobDescription = """
         My api tests description
         """
        try {
            currentBuild.description=jobDescription;
            def testContainerName = "api_tests_$BUILD_NUMBER"
            stage ("Run API Tests ${jobDescription}"){

                sh "docker run -e ${env.PROFILE} --rm --network=host --name $testContainerName -v $pwd/allure-results:/home/ubuntu/target/allure-results -t localhost:5005/apitests"
            }
            stage("Publish allure report"){
                allure([
                        disabled:true,
                        results:["$pwd/allure-results"]
                ])
            }
            stage("Telegram notification"){

            }
        }
        finally {
            sh "docker stop $jobDescription"
        }
    }
}