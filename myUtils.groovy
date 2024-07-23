def prepareConfig(){
    def yamlConfig = readYaml text : $YAML_CONFIG
    yamlConfig.each(k, v -> System.setProperty(k, v))
}



def triggerJob(def jobName, dev config){
    Job job= build job: $jobName,  parameters: ["YAML_CONFIG":config]
}

this