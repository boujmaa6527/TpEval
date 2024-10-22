
DROP DATABASE IF EXISTS Eval;
CREATE DATABASE Eval;
USE Eval;

CREATE TABLE T_Users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users ( Login, Password) VALUES ( 'sam' ,'pass1' );
INSERT INTO T_Users ( Login, Password) VALUES ( 'abdel' ,'pass3' );
INSERT INTO T_Users ( Login, Password) VALUES ( 'papin','pass4' );


SELECT *  FROM T_Users;

CREATE TABLE T_Clients (
	IdClient				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Nom				varchar(20)	NOT NULL UNIQUE,
	Prenom			varchar(20)	NOT NULL,
	Email  			varchar(30) NOT NULL,
	Adresse  		varchar(30) NOT NULL,
	Telephone  		varchar(30) NOT NULL,
	IdUser 			int(4) NOT NULL,
	FOREIGN KEY (IdUser) REFERENCES T_Users(IdUser)
	
) ENGINE = InnoDB;
INSERT INTO T_Clients ( Nom, Prenom, Email, Adresse, Telephone, IdUser ) VALUES ( 'benyedder' ,'wissam' , 'wissam@yahoo.fr','monaco', '0123456789',1);
INSERT INTO T_Clients ( Nom, Prenom, Email, Adresse, Telephone, IdUser  ) VALUES ( 'zidane' ,'zizou' , 'zizou.fr','marseille','0123456789',2);
INSERT INTO T_Clients ( Nom, Prenom, Email, Adresse, Telephone, IdUser  ) VALUES ( 'benzema' ,'ben' , 'ben.fr','lyon', '0123456789',1);
INSERT INTO T_Clients ( Nom, Prenom, Email, Adresse, Telephone, IdUser  ) VALUES ( 'benarfa' ,'arfa' , 'arfa.fr','newcastle','0123456789',2);
INSERT INTO T_Clients ( Nom, Prenom, Email, Adresse, Telephone, IdUser  ) VALUES ( 'salah' ,'moh' , 'salah@yahoo.fr','liverpool','0123456789',1);



CREATE TABLE T_Categories (
	IdCategory  int(4) PRIMARY KEY AUTO_INCREMENT,
	NomCategorie  varchar(30) NOT NULL,
	Description   varchar(255) NOT NULL
)ENGINE = InnoDB; 


INSERT INTO T_Categories ( NomCategorie, Description  ) VALUES ( 'Dev Web' ,'formation sur mesure');
INSERT INTO T_Categories ( NomCategorie, Description  ) VALUES ( 'Cms' ,'formation sur catalogue');
INSERT INTO T_Categories ( NomCategorie, Description  ) VALUES ( 'bureatique' ,'pour etre a la pointe des adminstrations');
INSERT INTO T_Categories ( NomCategorie, Description  ) VALUES ( 'IA' ,'Plus aucun secret sur les IA pour vous');
INSERT INTO T_Categories ( NomCategorie, Description  ) VALUES ( 'Cyber sécurité' ,'etre le prochain espion avec un clavier souris avec étique');



CREATE TABLE T_Formations (
	IdFormation int(4) PRIMARY KEY AUTO_INCREMENT,
	NomFormation varchar(30) NOT NULL, 
	DescriptionFormation varchar(255) NOT NULL, 
	DureeJour int(4) NOT NULL,
	DistancielPresentiel varchar(30) NOT NULL, 
	Prix float(4) NOT NULL, 
	IdCategory int(4), 
	FOREIGN KEY (idCategory) REFERENCES T_Categories(idCategory)
)ENGINE = InnoDB;

INSERT INTO T_Formations ( NomFormation, DescriptionFormation, DureeJour, DistancielPresentiel,Prix,  IdCategory   ) VALUES ( 'Java' ,'Java SE 8: syntaxe & poo', 20, 'presentiel',150, 1 );
INSERT INTO T_Formations ( NomFormation, DescriptionFormation, DureeJour, DistancielPresentiel,Prix, IdCategory   ) VALUES ( 'Java avancé' ,'Exeptions, fichier, jdbc, thread', 30, 'presentiel',150,1 );
INSERT INTO T_Formations ( NomFormation, DescriptionFormation, DureeJour, DistancielPresentiel,Prix, IdCategory   ) VALUES ( 'Spring' ,'Spring Core/mvc/Security', 40, 'presentiel',250,1 );
INSERT INTO T_Formations ( NomFormation, DescriptionFormation, DureeJour, DistancielPresentiel, Prix, IdCategory   ) VALUES ( 'Php frameworks' ,'Symphony', 15, 'distanciel',300,2);
INSERT INTO T_Formations ( NomFormation, DescriptionFormation, DureeJour, DistancielPresentiel, Prix, IdCategory   ) VALUES ( 'c#' ,'DotNet CORE', 30, 'distanciel',350,3 );

CREATE TABLE T_Commandes(

	IdCommande int(4) PRIMARY KEY AUTO_INCREMENT,
	Amount Float(4) NOT NULL, 
	DateCommande Date NOT NULL DEFAULT NOW(),
	IdUser int(4) NOT NULL,
	FOREIGN KEY(IdUser) REFERENCES T_Users(IdUser)
)ENGINE = InnoDB;
	












