import {
  BanknotesIcon,
  UserPlusIcon,
  UsersIcon,
  ChartBarIcon,
} from "@heroicons/react/24/solid";

// 데이터 예시를 기준으로 통계 카드 데이터 변경
export const statisticsCardsData = [
  {
    color: "gray",
    icon: ChartBarIcon,
    title: "Dow Jones",
    value: "$" + 43275.91.toFixed(2), // closePrice 사용
    footer: {
      color: "text-green-500",
      value: "+0.085%", // changePercentage 사용
      label: "than last close",
    },
  },
  {
    color: "gray",
    icon: UsersIcon,
    title: "S&P 500",
    value: "$" + 5864.67.toFixed(2), // closePrice 사용
    footer: {
      color: "text-green-500",
      value: "+0.397%", // changePercentage 사용
      label: "than last close",
    },
  },
  {
    color: "gray",
    icon: UserPlusIcon,
    title: "Nasdaq 100",
    value: "$" + 20324.04.toFixed(2), // closePrice 사용
    footer: {
      color: "text-green-500",
      value: "+0.661%", // changePercentage 사용
      label: "than last close",
    },
  },
  {
    color: "gray",
    icon: BanknotesIcon,
    title: "Russell 2000",
    value: "$" + 2276.09.toFixed(2), // closePrice 사용
    footer: {
      color: "text-red-500",
      value: "-0.208%", // changePercentage 사용
      label: "than last close",
    },
  },
];

export default statisticsCardsData;
