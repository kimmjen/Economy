from fastapi import APIRouter

finance_router = APIRouter()

@finance_router.get("/update")
async def update_finance():
    return {"status": "success"}