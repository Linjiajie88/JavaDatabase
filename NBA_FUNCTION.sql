DROP PROCEDURE  IF EXISTS SearchForPlayer;
DELIMITER ^^
CREATE PROCEDURE SearchForPlayer(player_name VARCHAR(15))
BEGIN
	SELECT PlayerName,SeasonStart,ORB,PTS 
    FROM nbanew 
    where Playername LIKE CONCAT ('%', player_name, '%');
END^^
DELIMITER ;

DROP PROCEDURE  IF EXISTS SearchForTeam;
DELIMITER ^^
CREATE PROCEDURE SearchForTeam(team_name VARCHAR(15))
BEGIN
	SELECT TM,SeasonStart,sum(ORB),SUM(PTS)
	FROM nbanew 
    where TM LIKE CONCAT ('%', team_name, '%')
	group by SeasonStart
	order by SeasonStart;
    
END^^
DELIMITER ;





