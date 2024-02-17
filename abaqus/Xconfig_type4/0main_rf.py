# -*- coding: mbcs -*-
# Do not delete the following import lines
from abaqus import *
from abaqusConstants import *
import __main__

import section
import regionToolset
import displayGroupMdbToolset as dgm
import part
import material
import assembly
import step
import interaction
import load
import mesh
import optimization
import job
import sketch
import visualization
import xyPlot
import displayGroupOdbToolset as dgo
import connectorBehavior
import os

os.chdir(r"C:\Temp\col181003")
mdb.openAuxMdb(pathName='kp_sp_set2.cae')
mdb.copyAuxMdbModel(fromName='Model-1', toName='Model-1')
mdb.closeAuxMdb()
execfile("material.py", __main__.__dict__)
execfile("beam.py", __main__.__dict__)
execfile("col.py", __main__.__dict__)
execfile("sp.py", __main__.__dict__)
execfile("bolt1.py", __main__.__dict__)
execfile("bolt2.py", __main__.__dict__)
execfile("bolt3.py", __main__.__dict__)
execfile("cfr.py", __main__.__dict__)
execfile("cwr.py", __main__.__dict__)
execfile("assemble.py", __main__.__dict__)
execfile("step.py", __main__.__dict__)
execfile("interaction.py", __main__.__dict__)
execfile("load.py", __main__.__dict__)


