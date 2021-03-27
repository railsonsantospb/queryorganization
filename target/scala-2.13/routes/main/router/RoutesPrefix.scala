// @GENERATOR:play-routes-compiler
// @SOURCE:/home/seven/Documents/GitHub/queryorganization/conf/routes
// @DATE:Sat Mar 27 07:52:02 BRT 2021


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
