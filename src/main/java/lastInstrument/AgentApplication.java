package lastInstrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.HashMap;

public class AgentApplication {
    public static HashMap<Class<?>, Long> hashMap = new HashMap<>();

    public static void agentmain(String args, Instrumentation instrumentation) throws UnmodifiableClassException {
        System.out.println("start");
        Class<?>[] classes = instrumentation.getAllLoadedClasses();
        for (Class<?> aClass : classes) {
            if (instrumentation.isModifiableClass(aClass)) {
                instrumentation.addTransformer(new ClassFileTransformer() {
                    @Override
                    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

                        if (aClass.getName().equals("packet.ConnectPacket")) {
                            System.out.println("reload " + aClass.getName());
                        }
                        return classfileBuffer;

                    }
                }, true);
                instrumentation.retransformClasses(aClass);
            }
        }
    }
}
