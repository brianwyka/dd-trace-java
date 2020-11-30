package test

import java.sql.Array
import java.sql.Blob
import java.sql.CallableStatement
import java.sql.Clob
import java.sql.Connection
import java.sql.DatabaseMetaData
import java.sql.NClob
import java.sql.PreparedStatement
import java.sql.SQLClientInfoException
import java.sql.SQLException
import java.sql.SQLWarning
import java.sql.SQLXML
import java.sql.Savepoint
import java.sql.Statement
import java.sql.Struct
import java.util.concurrent.Executor


/**
 * A JDBC connection class that optionally throws an exception in the constructor, used to test
 */
class TestConnection implements Connection {
  TestConnection(boolean throwException) {
    if (throwException) {
      throw new RuntimeException("connection exception")
    }
  }


  @Override
  Statement createStatement() throws SQLException {
    return new TestStatement(this)
  }

  @Override
  PreparedStatement prepareStatement(String sql) throws SQLException {
    return new TestPreparedStatement(this)
  }

  @Override
  CallableStatement prepareCall(String sql) throws SQLException {
    return null
  }

  @Override
  String nativeSQL(String sql) throws SQLException {
    return null
  }

  @Override
  void setAutoCommit(boolean autoCommit) throws SQLException {

  }

  @Override
  boolean getAutoCommit() throws SQLException {
    return false
  }

  @Override
  void commit() throws SQLException {

  }

  @Override
  void rollback() throws SQLException {

  }

  @Override
  void close() throws SQLException {

  }

  @Override
  boolean isClosed() throws SQLException {
    return false
  }

  @Override
  DatabaseMetaData getMetaData() throws SQLException {
    return new TestDatabaseMetaData()
  }

  @Override
  void setReadOnly(boolean readOnly) throws SQLException {

  }

  @Override
  boolean isReadOnly() throws SQLException {
    return false
  }

  @Override
  void setCatalog(String catalog) throws SQLException {

  }

  @Override
  String getCatalog() throws SQLException {
    return null
  }

  @Override
  void setTransactionIsolation(int level) throws SQLException {

  }

  @Override
  int getTransactionIsolation() throws SQLException {
    return 0
  }

  @Override
  SQLWarning getWarnings() throws SQLException {
    return null
  }

  @Override
  void clearWarnings() throws SQLException {

  }

  @Override
  Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
    return null
  }

  @Override
  PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
    return null
  }

  @Override
  CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
    return null
  }

  @Override
  Map<String, Class<?>> getTypeMap() throws SQLException {
    return null
  }

  @Override
  void setTypeMap(Map<String, Class<?>> map) throws SQLException {

  }

  @Override
  void setHoldability(int holdability) throws SQLException {

  }

  @Override
  int getHoldability() throws SQLException {
    return 0
  }

  @Override
  Savepoint setSavepoint() throws SQLException {
    return null
  }

  @Override
  Savepoint setSavepoint(String name) throws SQLException {
    return null
  }

  @Override
  void rollback(Savepoint savepoint) throws SQLException {

  }

  @Override
  void releaseSavepoint(Savepoint savepoint) throws SQLException {

  }

  @Override
  Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    return null
  }

  @Override
  PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    return null
  }

  @Override
  CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    return null
  }

  @Override
  PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
    return null
  }

  @Override
  PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
    return null
  }

  @Override
  PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
    return null
  }

  @Override
  Clob createClob() throws SQLException {
    return null
  }

  @Override
  Blob createBlob() throws SQLException {
    return null
  }

  @Override
  NClob createNClob() throws SQLException {
    return null
  }

  @Override
  SQLXML createSQLXML() throws SQLException {
    return null
  }

  @Override
  boolean isValid(int timeout) throws SQLException {
    return false
  }

  @Override
  void setClientInfo(String name, String value) throws SQLClientInfoException {

  }

  @Override
  void setClientInfo(Properties properties) throws SQLClientInfoException {

  }

  @Override
  String getClientInfo(String name) throws SQLException {
    throw new UnsupportedOperationException("Test 123")
  }

  @Override
  Properties getClientInfo() throws SQLException {
    throw new Throwable("Test 123")
  }

  @Override
  Array createArrayOf(String typeName, Object[] elements) throws SQLException {
    return null
  }

  @Override
  Struct createStruct(String typeName, Object[] attributes) throws SQLException {
    return null
  }

  @Override
  void setSchema(String schema) throws SQLException {

  }

  @Override
  String getSchema() throws SQLException {
    return null
  }

  @Override
  void abort(Executor executor) throws SQLException {

  }

  @Override
  void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

  }

  @Override
  int getNetworkTimeout() throws SQLException {
    return 0
  }

  @Override
  def <T> T unwrap(Class<T> iface) throws SQLException {
    return null
  }

  @Override
  boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false
  }
}
