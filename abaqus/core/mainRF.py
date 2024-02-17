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

os.chdir(r"G:\abaqus")
mdb.openAuxMdb(pathName='kp_sp_set2.cae')
mdb.copyAuxMdbModel(fromName='Model-1', toName='Model-1')
mdb.closeAuxMdb()
execfile("material.py", __main__.__dict__)
execfile("sp.py", __main__.__dict__)
execfile("beam.py", __main__.__dict__)
execfile("bolt1.py", __main__.__dict__)
execfile("bolt2.py", __main__.__dict__)
execfile("bolt3.py", __main__.__dict__)
execfile("tp.py", __main__.__dict__)
execfile("endplate.py", __main__.__dict__)


execfile("assemble.py", __main__.__dict__)
execfile("step.py", __main__.__dict__)
execfile("interaction.py", __main__.__dict__)
execfile("load.py", __main__.__dict__)

execfile("set.py", __main__.__dict__)


#-------------------------------------------------------------------------------------------

#inpファイルの書き出し。たぶんワークディレクトリに書き出されるものと思われる。
mdb.Job(name='Job-1', model='Model-1', description='', type=ANALYSIS, 
atTime=None, waitMinutes=0, waitHours=0, queue=None, memory=90, 
memoryUnits=PERCENTAGE, getMemoryFromAnalysis=True, 
explicitPrecision=SINGLE, nodalOutputPrecision=SINGLE, echoPrint=OFF, 
modelPrint=OFF, contactPrint=OFF, historyPrint=OFF, userSubroutine='', 
scratch='', parallelizationMethodExplicit=DOMAIN, numDomains=1, 
activateLoadBalancing=False, multiprocessingMode=DEFAULT, numCpus=1)
mdb.jobs['Job-1'].writeInput(consistencyChecking=OFF)
