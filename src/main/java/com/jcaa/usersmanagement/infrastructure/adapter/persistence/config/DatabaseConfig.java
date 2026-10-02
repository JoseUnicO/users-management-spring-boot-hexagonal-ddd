package com.jcaa.usersmanagement.infrastructure.adapter.persistence.config;

public record DatabaseConfig(
    String host,
    int port,
    String databaseName,
    String username,
    String password,
    String sslMode,
    String vendor) {
  private static final String MYSQL_URL_TEMPLATE =
      "jdbc:mysql://%s:%d/%s?sslMode=%s&serverTimezone=UTC&allowPublicKeyRetrieval=true";
  private static final String POSTGRESQL_URL_TEMPLATE =
      "jdbc:postgresql://%s:%d/%s?sslmode=%s";

  public DatabaseConfig(
      final String host,
      final int port,
      final String databaseName,
      final String username,
      final String password,
      final String sslMode) {
    this(host, port, databaseName, username, password, sslMode, "mysql");
  }

  public String buildJdbcUrl() {
    if ("mysql".equalsIgnoreCase(vendor)) {
      return String.format(MYSQL_URL_TEMPLATE, host, port, databaseName, sslMode);
    }
    if ("postgresql".equalsIgnoreCase(vendor)) {
      return String.format(POSTGRESQL_URL_TEMPLATE, host, port, databaseName, sslMode);
    }
    throw new IllegalArgumentException("Unsupported database vendor: " + vendor);
  }
}
