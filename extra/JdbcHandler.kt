package sql

import com.mysql.cj.jdbc.MysqlDataSource
import global.AppConfig
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

object JdbcHandler {

    var connection: Connection? = null
    private var connectionState: Boolean = false
    var ps: PreparedStatement? = null
    var logger: org.slf4j.Logger = LoggerFactory.getLogger("SQL")

    private fun getAndSetConnection(): Connection? {
        if (connection == null) {
            val dataSource = MysqlDataSource()
            dataSource.databaseName = AppConfig.DATABASE_NAME
            dataSource.port = AppConfig.DATABASE_PORT
            dataSource.serverName = AppConfig.DATABASE_HOST
            dataSource.user = AppConfig.DATABASE_USERNAME
            dataSource.password = AppConfig.DATABASE_PASSWORD
            try {
                connection = dataSource.connection
            } catch (e: SQLException) {
                logger.error("Connection to db failed.", e)
                throw DbServiceException(e)
            }
        }
        return connection
    }

    fun checkConnection() {
        try {
            connectionState = getAndSetConnection()!!.isClosed
        } catch (e1: SQLException) {
            logger.error("Connection to db failed or closed!", e1)
        }
        if(connectionState) {
            logger.error("Connection to db seems closed!")
        }
    }

    fun isDbConnected() : Boolean {
        checkConnection()
        return !connectionState
    }
}