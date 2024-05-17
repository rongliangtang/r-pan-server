SELECT CONCAT('SELECT ', ndxcollist, ' FROM ', db, '.', tb, ' ORDER BY ', ndxcollist,
              ';') SelectQueryToLoadCache
FROM (SELECT ENGINE,
             table_schema                                    db,
             table_name                                      tb,
             index_name,
             GROUP_CONCAT(column_name ORDER BY seq_in_index) ndxcollist
      FROM (SELECT B.ENGINE,
                   A.table_schema,
                   A.table_name,
                   A.index_name,
                   A.column_name,
                   A.seq_in_index
            FROM information_schema.statistics A
                     INNER JOIN (SELECT ENGINE, table_schema, table_name
                                 FROM information_schema.TABLES
                                 WHERE ENGINE = 'InnoDB') B USING (table_schema, table_name)
            WHERE B.table_schema NOT IN ('information_schema', 'mysql')
            ORDER BY table_schema,
                     table_name,
                     index_name,
                     seq_in_index) A
      GROUP BY table_schema,
               table_name,
               index_name) AA
ORDER BY db,
         tb;