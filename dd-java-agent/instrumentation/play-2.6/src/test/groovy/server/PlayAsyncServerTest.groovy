package server

import play.BuiltInComponents
import play.Mode
import play.libs.concurrent.HttpExecution
import play.mvc.Results
import play.routing.RoutingDsl
import play.server.Server
import spock.lang.Shared

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.function.Supplier

import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.ERROR
import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.EXCEPTION
import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.FORWARDED
import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.NOT_FOUND
import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.QUERY_PARAM
import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.REDIRECT
import static datadog.trace.agent.test.base.HttpServerTest.ServerEndpoint.SUCCESS

class PlayAsyncServerTest extends PlayServerTest {
  @Shared
  def executor = Executors.newCachedThreadPool()

  def cleanupSpec() {
    executor.shutdown()
  }

  @Override
  Server startServer(int port) {
    def execContext = HttpExecution.fromThread(executor)
    return Server.forRouter(Mode.TEST, port) { BuiltInComponents components ->
      RoutingDsl.fromComponents(components)
        .GET(SUCCESS.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(SUCCESS) {
              Results.status(SUCCESS.getStatus(), SUCCESS.getBody())
            }
          }, execContext)
        } as Supplier)
        .GET(FORWARDED.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(FORWARDED) {
              Results.status(FORWARDED.getStatus(), FORWARDED.getBody()) // cheating
            }
          }, execContext)
        } as Supplier)
        .GET(QUERY_PARAM.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(QUERY_PARAM) {
              Results.status(QUERY_PARAM.getStatus(), QUERY_PARAM.getBody()) // cheating
            }
          }, execContext)
        } as Supplier)
        .GET(REDIRECT.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(REDIRECT) {
              Results.found(REDIRECT.getBody())
            }
          }, execContext)
        } as Supplier)
        .GET(ERROR.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(ERROR) {
              Results.status(ERROR.getStatus(), ERROR.getBody())
            }
          }, execContext)
        } as Supplier)
        .GET(EXCEPTION.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(EXCEPTION) {
              throw new Exception(EXCEPTION.getBody())
            }
          }, execContext)
        } as Supplier)
        .GET(NOT_FOUND.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(NOT_FOUND) {
              Results.status(NOT_FOUND.getStatus(), NOT_FOUND.getBody())
            }
          }, execContext)
        } as Supplier)
        .GET(NOT_FOUND.getPath()).routeAsync({
          CompletableFuture.supplyAsync({
            controller(ERROR) {
              throw new Exception("not found")
            }
          })
        } as Supplier)
        .build()
    }
  }
}
