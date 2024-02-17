#--------------------------------------
p = mdb.models['Model-1'].parts[cpartname]
session.viewports['Viewport: 1'].setValues(displayedObject=p)
p = mdb.models['Model-1'].parts[cpartname]
p.features['Solid extrude-1'].setValues(depth=ts)
p = mdb.models['Model-1'].parts[cpartname]
p.regenerate()
p = mdb.models['Model-1'].parts[cpartname]
p.regenerate()
session.viewports['Viewport: 1'].partDisplay.setValues(sectionAssignments=OFF, engineeringFeatures=OFF, mesh=ON)
session.viewports['Viewport: 1'].partDisplay.meshOptions.setValues(meshTechnique=ON)
p = mdb.models['Model-1'].parts[cpartname]
p.generateMesh()



del mdb.models['Model-1'].parts['sp400_M22-4'].sectionAssignments[0]
mdb.models['Model-1'].parts['sp400_M22-4'].SectionAssignment(offset=0.0, 
    offsetField='', offsetType=MIDDLE_SURFACE, region=Region(
    cells=mdb.models['Model-1'].parts['sp400_M22-4'].cells.getSequenceFromMask(
    mask=('[#7f ]', ), )), sectionName=mat, thicknessAssignment=
    FROM_SECTION)
# Save by 14analysis on 2018_10_30-15.37.24; build 6.12-1 2012_03_13-20.23.18 119612

