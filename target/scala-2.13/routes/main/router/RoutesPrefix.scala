// @GENERATOR:play-routes-compiler
// @SOURCE:/home/seven/queryorganization/conf/routes
// @DATE:Fri Mar 26 17:45:52 BRT 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
