ALTER TABLE user_
CHANGE created_user user_id int;

ALTER TABLE user_ DROP COLUMN modified_user;
ALTER TABLE user_ DROP COLUMN modified_date;
