

timeout(60){
    node("master"){
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
//            stage("Publish allure report"){
//                allure([
//                        disabled:true,
//                        results:["$pwd/allure-results"]
//                ])
//            }
//            stage("Telegram notification"){
//
//            }
        }
        finally {
            sh "docker stop $jobDescription"
        }
    }
}

def prepareConfig(){
    def yamlConfig = readYaml text : $YAML_CONFIG
    yamlConfig.each { k, v -> System.setProperty(k, v)}
}

def triggerJob(def jobName) {
    build job: "$jobName"
}

//def triggerJob(def jobName, dev config){
//    Job job= build job: $jobName,  parameters: ["YAML_CONFIG":config]
//}