from aenum import Enum, NoAlias, auto

class ExtendedEnum(Enum):
    @classmethod
    def values(cls):
        return list(map(lambda c: c.value, cls))

    @classmethod
    def names(cls):
        return list(map(lambda c: c.name, cls))