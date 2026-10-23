package app.epxec.patches.xeq

import app.morphe.patcher.patch.bytecodePatch
import app.epxec.patches.shared.Constants.COMPATIBILITY_XEQ
import app.epxec.patches.xeq.Fingerprints.XEQProFingerprint
import app.morphe.patches.all.misc.fix.changepackageinstaller.changePackageInstallerPatch
import app.morphe.util.addInstructionsAtControlFlowLabel

@Suppress("unused")
val enableVipPatch = bytecodePatch(
    name = "Enable Pro",
    description = "Enable Pro features",
    default = true
) {
    compatibleWith(COMPATIBILITY_XEQ)

    dependsOn(changePackageInstallerPatch())

    execute {
        val instructionMatch = XEQProFingerprint.instructionMatches[0]

        XEQProFingerprint.method.addInstructionsAtControlFlowLabel(
            instructionMatch.index,
            """
                invoke-virtual {v0}, Let1;->i()V
            """
        )
    }
}
