package com.tjl.database
/*
class Cassandra {

  Cluster cluster = null;
  try {
    cluster = Cluster
      .builder()
      .addContactPoint("127.0.0.1")
      .build();
    Session session = cluster.connect();

    ResultSet rs = session.execute("select release_version from system.local");
    Row row = rs.one();
    System.out.println(row.getString("release_version"));
  } finally if (cluster != null) cluster.close();

}*/
