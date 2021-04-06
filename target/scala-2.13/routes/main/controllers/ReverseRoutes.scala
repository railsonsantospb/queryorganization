// @GENERATOR:play-routes-compiler
// @SOURCE:/home/seven/Documents/GitHub/queryorganization/conf/routes
// @DATE:Mon Apr 05 20:40:42 BRT 2021

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(orgName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/organizations/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("orgName", orgName)) + "/contributors")
    }
  
  }

  // @LINE:9
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
