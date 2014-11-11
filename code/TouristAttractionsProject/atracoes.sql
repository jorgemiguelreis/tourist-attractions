
CREATE TABLE 'category' (
	'cid'	TEXT NOT NULL PRIMARY KEY,
	'name'	TEXT NOT NULL
);

CREATE TABLE 'categories_inheritance' (
	'cid2'		TEXT NOT NULL,
	'subcat'	TEXT NOT NULL,
	FOREIGN KEY(cid2) REFERENCES category(cid),
	FOREIGN KEY(subcat) REFERENCES category(cid)
	PRIMARY KEY(cid2, subcat)
);


