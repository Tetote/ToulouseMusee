class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"museum",
            action:"index"
        )

        "/visitrequest"(controller:"visitRequest",
                            action:"index"
        )

        "500"(view:'/error')
	}
}
