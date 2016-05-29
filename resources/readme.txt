ROMSetMerger 0.10
=================

A small util to help organizing TOSEC based ROMSets.

It will merge all corresponding Versions of a game into one, while preserving filenames.

Usage:
	java -jar ROMSetMerger-X.XX.jar "[FULL PATH to your DAT]"

The result will be stored under [FULL PATH to your DAT]_merged, e.g. The result of "C:/Datasets/Fantasysystem.dat" will be stored under "C:/Datasets/Fantasysystem.dat_merged".

Please keep in mind that this util has rudimentary error handling only so don't use backslash (\) in path - if you have to, use em doubled (\\) and expect errors on exotic DATs.

Also this tool won't recognize DATs with <machine> instead of <game>-Tags - they are currently produced by clmamepro64's Dir2Dat option.
This is intended because <machine>-Tag is no part of "ROM Management Datafile" (see http://www.logiqx.com/Dats/datafile.dtd).


Copyright 2016
_-pYRo_-