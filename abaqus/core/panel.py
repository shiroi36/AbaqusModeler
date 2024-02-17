

#スケッチの単なる読み込み
from dxf2abq import importdxf
importdxf(fileName='lib/'+s0+'.dxf')
#-------------------------------------------------------------------------------------------

s1 = mdb.models['Model-1'].ConstrainedSketch(name='__profile__', 
sheetSize=200.0)
g, v, d, c = s1.geometry, s1.vertices, s1.dimensions, s1.constraints
s1.setPrimaryObject(option=STANDALONE)
s1.sketchOptions.setValues(gridOrigin=(0.0, 0.0))
s1.retrieveSketch(sketch=mdb.models['Model-1'].sketches['panel'])
session.viewports['Viewport: 1'].view.fitView()
p = mdb.models['Model-1'].Part(name=cpartname, dimensionality=THREE_D, 
type=DEFORMABLE_BODY)
p = mdb.models['Model-1'].parts[cpartname]
p.BaseSolidExtrude(sketch=s1, depth=tp)
s1.unsetPrimaryObject()
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
del mdb.models['Model-1'].sketches['__profile__']
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#1 ]', ), )
e, v1, d1 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e[0], cells=pickedCells, 
point=p.InterestingPoint(edge=e[0], rule=MIDDLE))
p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
pickedCells = c.getSequenceFromMask(mask=('[#3 ]', ), )
e1, v2, d2 = p.edges, p.vertices, p.datums
p.PartitionCellByPlanePointNormal(normal=e1[11], cells=pickedCells, 
point=p.InterestingPoint(edge=e1[11], rule=MIDDLE))
session.viewports['Viewport: 1'].partDisplay.setValues(mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=ON)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=OFF)
p = mdb.models['Model-1'].parts[cpartname]
p.seedPart(size=25, deviationFactor=0.1, minSizeFactor=0.1)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()
session.viewports['Viewport: 1'].partDisplay.setValues(mesh=OFF)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(
meshTechnique=OFF)
session.viewports['Viewport: 1'].partDisplay.geometryOptions.setValues(
referenceRepresentation=ON)



#-------------------------------------------------------------------------------------------


p = mdb.models['Model-1'].parts[cpartname]
c = p.cells
cells = c.getSequenceFromMask(mask=('[#f ]', ), )
region = regionToolset.Region(cells=cells)
p = mdb.models['Model-1'].parts[cpartname]
p.SectionAssignment(region=region, sectionName=mat, offset=0.0, 
offsetType=MIDDLE_SURFACE, offsetField='', 
thicknessAssignment=FROM_SECTION)


p = mdb.models['Model-1'].parts[cpartname]
f = p.faces
faces = f.getSequenceFromMask(mask=('[#80460 ]', ), )
p.Set(faces=faces, name='Set-1')






