def call(){
    script{
        //Setup PowerVS cluster config
        //Min config
            env.BASTION_MEMORY = "16"
            env.BASTION_PROCESSORS = "1"

            env.BOOTSTRAP_MEMORY = "16"
            env.BOOTSTRAP_PROCESSORS = ".5"

            env.NUM_OF_MASTERS = "3"
            env.MASTER_PROCESSORS = ".5"
            env.MASTER_MEMORY = "64"

            env.NUM_OF_WORKERS = 3
            env.WORKER_PROCESSORS = "1.25"
            env.WORKER_MEMORY = "64"
    }
}
