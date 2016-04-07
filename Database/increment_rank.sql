DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `increment_rank`(
		IN start_rank int
	)
BEGIN

    UPDATE post

    SET post.display_order = post.display_order + 1

    WHERE post.display_order > start_rank;

  END$$
DELIMITER ;

