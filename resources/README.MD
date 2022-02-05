ROMSetMerger 0.22a
==================
A small util to help organizing TOSEC based ROMSets.
It will merge all corresponding Versions of a game into one, while preserving filenames.


Available Parameters:
        -f, --datafile = Full qualified pathname of the DAT file to cleanup.
        -an, --alternate = ROMs marked as alternative version [a] will be filtered.
        -bn, --bad = ROMs marked as bad dump [b] will be filtered.
        -hn, --hack = ROMs marked as hacked [h] will be filtered.
        -mn, --modified = ROMs marked as modified [m] will be filtered.
        -tn, --trainer = ROMs marked as trained [t] will be filtered.
        -vn, --virus = ROMs marked as virus infected [v] will be filtered.
        -rx, --regex = using to filter by custom regular expression (Java compatible).

Usage: java -jar ROMSetMerger.jar -f /home/vintage.DAT -bn -vn -rx "(supadupa kangaroo)"
This will shorten vintag.DAT while removing all files marked as bad dump [b] or virus infected [v].


The result will be stored under "[FULL PATH to your DAT]_merged.dat", e.g. The result of "C:/Datasets/Fantasysystem.dat" will be stored under "C:/Datasets/Fantasysystem_merged.dat".

Please keep in mind that this util is quite untested yet, so don't blame me if it accidently does you any harm.


Also this tool won't find any games in DATs with <machine> instead of <game>-Tags - they are currently produced by clmamepro64's Dir2Dat option.
This is intended because <machine>-Tag is no part of "ROM Management Datafile" (see http://www.logiqx.com/Dats/datafile.dtd).


Copyright 2016-2018
_-pYRo_-



History
=======
0.22 (2018-10-24)
	+ Support for unsorted Datafilesn parentheses)
0.21 (2016-06-13)
	+	Support for filterign by custom regular expression
	#	Games without any ROM will be filtered

	# Support for merging of Rev(ision) Tag in Gamename (not betwee
0.20 (2016-05-29)
	+	Support for categorized filtering ([b], [a], ...)
	#	Exported DAT lacked the XML-Prologue thus not being recgnozed by clrmamepro

0.10 (2016-05-28)
	+	Initial version with ability to merge ROMs of various game variants into a single game/diskset
