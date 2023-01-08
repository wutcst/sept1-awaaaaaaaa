TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`127.0.0.1` trigger `t_message_update`\nafter insert on `log` for each row\nbegin\n	if(new.`transport_type`=1) then\n	update `transport`\n    set `now_handle`=new.`next_handle`,`transport_type`=new.`transport_type`,`now_handle_phone`=new.`next_handle_phone`\n    where `transport_id`=new.`transport_id`;\n    else\n    update `transport`\n    set `transport_type`=new.`transport_type`\n    where `transport_id`=new.`transport_id`;\n    end if;\nend'
sql_modes=0
definers='root@127.0.0.1'
client_cs_names='utf8'
connection_cl_names='utf8_general_ci'
db_cl_names='gbk_chinese_ci'
